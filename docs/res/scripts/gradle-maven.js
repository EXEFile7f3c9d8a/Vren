export function gradleF(){
    return "repositories{\n" +
           "     mavenCentral()\n" +
           "     maven { url 'https://jitpack.io' }\n" +
           "}"
}
export function gradleS(str){
    return "dependencies{\n" +
           `     implementation 'com.github.EXEFile7f3c9d8a:Vren:${str}'\n` +
           "}"
}
export function mavenF(){
    return "<repositories>\n" +
           "      <repository>\n" +
           "          <id>jitpack.io</id>\n" +
           "          <url>https://jitpack.io</url>\n" +
           "      </repository>\n" +
           "</repositories>"
}
export function mavenS(str){
    return "<dependencies>\n" +
           "      <dependency>\n" +
           "          <groupId>com.github.EXEFile7f3c9d8a</groupId>\n" +
           "          <artifactId>Vren</artifactId>\n" +
           `          <version>${str}</version>\n` +
           "      </dependency>\n" +
           "</dependencies>"
}