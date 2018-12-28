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
        (~/(?i)int/)                      : "Integer",
        (~/(?i)float|double|real/)        : "Double",
        (~/(?i)decimal/)                  : "BigDecimal",
        (~/(?i)datetime|timestamp/)       : "Date",
        (~/(?i)date/)                     : "Date",
        (~/(?i)time/)                     : "java.sql.Time",
        (~/(?i)bit/)                      : "Boolean",
        (~/(?i)/)                         : "String"
]

FILES.chooseDirectoryAndSave("Choose directory", "Choose where to store generated files") { dir ->
  SELECTION.filter { it instanceof DasTable && it.getKind() == ObjectKind.TABLE }.each { generate(it, dir) }
}

def generate(table, dir) {
  def className = javaName(table.getName(), true)
  def fields = calcFields(table)
  new File(dir, className.replace('_', '') + ".java").withPrintWriter { out -> generate(out, className, fields) }
}

def generate(out, className, fields) {
  out.println "package $packageName"
  out.println ""
  out.println ""
  out.println "import org.jsondoc.core.annotation.*;"
  out.println "import javax.persistence.*;"
  out.println "import javax.xml.bind.annotation.*;"
  out.println "import com.accentrix.lifetouch.domain.AuditedObject;"
  out.println ""
  out.println "@XmlRootElement"
  out.println "@XmlAccessorType(XmlAccessType.PROPERTY)"
  out.println "@Entity"
  out.println "@Table(name = \"${className.toLowerCase()}\")"
  out.println "@ApiObject(name = \"${className.replace('_', '')}\")"
  out.println "public class ${className.replace('_', '')} extends AuditedObject {"
  out.println ""
  fields.each() {
    if (it.annos != "") out.println "  ${it.annos}"

    if (it.name == 'id') {
      return
    }
    if (it.name == 'remarks') {
      return
    }
    if (it.name == 'create_By') {
      return
    }
    if (it.name == 'create_Date') {
      return
    }
    if (it.name == 'update_By') {
      return
    }
    if (it.name == 'update_Date') {
      return
    }
    if (it.name == 'del_Flag') {
      return
    }
    if (it.name == 'version') {
      return
    }

    out.println "  @Column(name = \"${it.name.toLowerCase()}\")"
    out.println "  @ApiObjectField(description = \"${it.name.replace('_', '')}\")"
    out.println "  private ${it.type} ${it.name.replace('_', '')};"
    out.println ""
  }
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
  capitalize || s.length() == 1? s : Case.LOWER.apply(s[0]) + s[1..-1]
}