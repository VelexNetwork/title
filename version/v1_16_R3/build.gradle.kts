plugins {
  id("project.publishing-conventions")
}

dependencies {
  api(project(":api"))
  
  compileOnly(libs.nms16)
  implementation(libs.annotations)
}