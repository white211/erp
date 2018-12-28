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
    new File(dir, className.replace('_', '') + "ServiceImpl" + ".java").withPrintWriter { out -> generate(out, className, fields, classNameFirstCharLowner) }
}

def generate(out, className, fields,classNameFirstCharLowner) {
    out.println "package $packageName"
    out.println ""
    out.println "import java.util.List;"
    out.println "import javax.ws.rs.Path;"
    out.println "import org.springframework.beans.factory.annotation.Autowired;"
    out.println "import org.springframework.stereotype.Service;"
    out.println "import org.springframework.transaction.annotation.Transactional;"
    out.println "import com.accentrix.nttca.dcms.common.timer.annotation.Timer;"
    out.println ""
    out.println ""
    out.println ""
    out.println ""
    out.println ""
    out.println "@Service"
    out.println "@Transactional(readOnly = true)"
    out.println "@Timer"
    out.println "@Path(\"${classNameFirstCharLowner}\")"
    out.println "public class ${className.replace('_', '')}ServiceImpl implements ${className.replace('_', '')}Service {"
    out.println ""
    out.println "@Autowired"
    out.println "private ${className.replace('_', '')}Repository ${classNameFirstCharLowner}Repository;"
    out.println ""
    out.println "@Override"
    out.println "public ${className.replace('_', '')} get(String id) {"
    out.println "return ${classNameFirstCharLowner}Repository.findOne(id);"
    out.println "}"
    out.println ""
    out.println "@Override"
    out.println "public ${className.replace('_', '')} save(${className.replace('_', '')} ${classNameFirstCharLowner}) {"
    out.println "return ${classNameFirstCharLowner}Repository.save(${classNameFirstCharLowner});"
    out.println "}"
    out.println ""
    out.println "@Override"
    out.println "public List<${className.replace('_', '')}> save(List<${className.replace('_', '')}> ${classNameFirstCharLowner}s) {"
    out.println "return ${classNameFirstCharLowner}Repository.save(${classNameFirstCharLowner}s);"
    out.println "}"
    out.println ""
    out.println "@Override"
    out.println "public void delete(${className.replace('_', '')} ${classNameFirstCharLowner}) { "
    out.println "${classNameFirstCharLowner}.setDelFlag(true);"
    out.println "${classNameFirstCharLowner}Repository.save(${classNameFirstCharLowner});"
    out.println "}"
    out.println ""
    out.println "@Override"
    out.println "public void delete(String id) {"
    out.println "${className.replace('_', '')} ${classNameFirstCharLowner} = ${classNameFirstCharLowner}Repository.findOne(id);"
    out.println "${classNameFirstCharLowner}.setDelFlag(true);"
    out.println "${classNameFirstCharLowner}Repository.save(${classNameFirstCharLowner});"
    out.println "}"
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
    def firstChar = str.substring(0, 1)
    def otherChar = str.substring(1, str.length())
    return firstChar.toLowerCase() + otherChar
}