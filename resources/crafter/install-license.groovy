import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths

import static utils.EnvironmentUtils.*
import static utils.ScriptUtils.*

LICENSE_FILE_NAME = 'crafter.lic'

def installLicense(license) {
  def licenseFile = Paths.get(license)
  if(!Files.exists(licenseFile)) {
    println "File $license doesn't exist or can't be read"
    return
  }
  def licenseFolder = Paths.get(getEnv('CRAFTER_HOME'), 'apache-tomcat/shared/classes/crafter/license')
  def installedLicense = licenseFolder.resolve(LICENSE_FILE_NAME)
  if(!Files.exists(licenseFolder)) {
    println 'No license found, creating folder and installing new license'
    Files.createDirectories(licenseFolder)
  } else if(!Files.exists(installedLicense)) {
    println 'No license found, installing new license'
  } else {
    println 'Previous license found, backing up and installing new license'
    def licenseBackup = licenseFolder.resolve(LICENSE_FILE_NAME + ".${new Date().format('YYYYMMddHHmm')}")
    Files.move(installedLicense, licenseBackup)
  }
  Files.copy(licenseFile, installedLicense)
  println installedLicense
  println 'License installation complete'
}

def cli = new CliBuilder(usage: 'install-license <license file>')
cli.h(longOpt: 'help', 'Show usage information')

def options = cli.parse(args)

if(options && options.arguments()) {
  installLicense(options.arguments()[0])
} else {
  cli.usage()
}