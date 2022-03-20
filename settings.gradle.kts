rootProject.name = "WebCore"

fun includeProject(name: String, dir: String? = null){
    include(name)
    dir?.let { project(name).projectDir = file(it) }
}
includeProject(":SmartHTTP","WebServer/SmartHTTP")
includeProject(":Rythm","TempleEngine/Rythm")