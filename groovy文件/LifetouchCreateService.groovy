import com.intellij.database.model.DasTable
import com.intellij.database.model.ObjectKind
import com.intellij.database.util.Case
import com.intellij.database.util.DasUtil

/*
 * Available context bindings:
 *   SELECTION   Iterable<DasObject>
 *   PROJECT     project
 *   FILES       files helper
 */

packageName = "com.sample;"
typeMapping = [
        (~/(?i)int/)               : "Integer",
        (~/(?i)float|double|real/) : "Double",
        (~/(?i)decimal/)           : "BigDecimal",
        (~/(?i)datetime|timestamp/): "Date",
        (~/(?i)date/)              : "Date",
        (~/(?i)time/)              : "java.sql.Time",
        (~/(?i)bit/)               : "Boolean",
        (~/(?i)/)                  : "String"
]

FILES.chooseDirectoryAndSave("Choose directory", "Choose where to store generated files") { dir ->
    SELECTION.filter { it instanceof DasTable && it.getKind() == ObjectKind.TABLE }.each {
        generate(it, dir)
    }
}

def generate(table, dir) {
    def className = javaName(table.getName(), true)
    def fields = calcFields(table)
    def classNameFirstCharLowner = firstCharLowner(className.replace('_', ''))
    new File(dir, className.replace('_', '') + "Service" + ".java").withPrintWriter { out -> generate(out, className, fields,classNameFirstCharLowner) }
}

def generate(out, className, fields, classNameFirstCharLowner) {
    out.println "package $packageName"
    out.println ""
    out.println ""
    out.println "import java.util.List;"
    out.println "import javax.ws.rs.*;"
    out.println "import javax.ws.rs.core.MediaType;"
    out.println "import org.jsondoc.core.annotation.*;"
    out.println "import org.jsondoc.core.pojo.ApiVerb;"
    out.println ""
    out.println "@Consumes({ MediaType.APPLICATION_JSON, MediaType.TEXT_XML })"
    out.println "@Produces({ MediaType.APPLICATION_JSON, MediaType.TEXT_XML })"
    out.println "@Api(name = \"${className.replace('_', '')} Service\", description = \"Methods for managing ${className.replace('_', '')}\")"
    out.println "public interface ${className.replace('_', '')}Service {"
    out.println ""
    out.println "@GET"
    out.println "@Path(\"/{id}\")"
    out.println "@ApiMethod(path = \"/api/rest/${classNameFirstCharLowner}/{id}\", verb = ApiVerb.GET, description = \"get ${className.replace('_', '')} by id\", produces = { MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })"
    out.println "@ApiResponseObject"
    out.println "${className.replace('_', '')} get(@ApiPathParam(name = \"id\", description = \"The ${className.replace('_', '')} id\") @PathParam(\"id\") String id);"
    out.println ""
    out.println "@POST"
    out.println "@Path(\"/\")"
    out.println "@ApiMethod(path = \"/api/rest/${classNameFirstCharLowner}/\", verb = ApiVerb.POST, description = \"save ${className.replace('_', '')}\", produces = { MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })"
    out.println "${className.replace('_', '')} save(${className.replace('_', '')} ${classNameFirstCharLowner});"
    out.println ""
    out.println "@POST"
    out.println "@Path(\"/save-list/\")"
    out.println "@ApiHeaders(headers = { @ApiHeader(name = \"UserId\", description = \"The login user id\") })"
    out.println "@ApiMethod(path = \"/api/rest/${classNameFirstCharLowner}/save-list/\", verb = ApiVerb.POST, description = \"save ${className.replace('_', '')}s\", produces = {  MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })"
    out.println "@ApiResponseObject"
    out.println "List<${className.replace('_', '')}> save(List<${className.replace('_', '')}> ${classNameFirstCharLowner}s);"
    out.println ""
    out.println "@DELETE"
    out.println "@Path(\"/delete\")"
    out.println "@ApiMethod(path = \"/api/rest/${classNameFirstCharLowner}/delete\", verb = ApiVerb.DELETE, description = \"delete ${className.replace('_', '')}\", produces = {  MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })"
    out.println "void delete(${className.replace('_', '')} ${classNameFirstCharLowner});"
    out.println ""
    out.println "@DELETE"
    out.println "@Path(\"/{id}/\")"
    out.println "@ApiHeaders(headers = { @ApiHeader(name = \"UserId\", description = \"The login user id\") })"
    out.println "@ApiMethod(path = \"/api/rest/${classNameFirstCharLowner}/delete/{id}/\", verb = ApiVerb.DELETE, description = \"delete ${className.replace('_', '')} by id\", produces = { MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })"
    out.println "void delete(@ApiPathParam(name = \"id\", description = \"The ${className.replace('_', '')} id\") @PathParam(\"id\") String id);"
    out.println ""
    out.println ""
    out.println "}"
}

def calcFields(table) {
    DasUtil.getColumns(table).reduce([]) { fields, col ->
        def spec = Case.LOWER.apply(col.getDataType().getSpecification())
        def typeStr = typeMapping.find { p, t -> p.matcher(spec).find() }.value
        fields += [[
                           name : javaName(col.getName(), false),
                           type : typeStr,
                           annos: ""]]
    }
}

def javaName(str, capitalize) {
    def s = str.split(/(?<=[^\p{IsLetter}])/).collect { Case.LOWER.apply(it).capitalize() }
            .join("").replaceAll(/[^\p{javaJavaIdentifierPart}]/, "_")
    capitalize || s.length() == 1 ? s : Case.LOWER.apply(s[0]) + s[1..-1]
}

def firstCharLowner(str) {
    def firstChar = str.substring(0,1)
    def otherChar = str.substring(1,str.length())
    return firstChar.toLowerCase()+otherChar
}