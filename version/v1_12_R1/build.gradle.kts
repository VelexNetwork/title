plugins {
  id("project.publishing-conventions")
}

dependencies {
  api(project(":api"))
  
  compileOnly(libs.nms12)
  implementation(libs.annotations)
}
