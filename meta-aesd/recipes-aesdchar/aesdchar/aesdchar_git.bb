# Recipe created by recipetool
# This is the basis of a recipe and may need further editing in order to be fully functional.
# (Feel free to remove these comments when editing.)

# WARNING: the following LICENSE and LIC_FILES_CHKSUM values are best guesses - it is
# your responsibility to verify that the values are complete and correct.
#
# The following license files were not able to be identified and are
# represented as "Unknown" below, you will need to check them yourself:
#   LICENSE
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"


SRC_URI = "git://git@github.com/cu-ecen-aeld/assignments-3-and-later-polandru.git;protocol=ssh;branch=master"

inherit module
FILES:${PN} += "${sysconfdir}/init.d/aesdchar_start_stop"
FILES:${PN} += "${bindir}/aesdchar_load"
FILES:${PN} += "${bindir}/aesdchar_unload"

	 
	       



# Modify these as desired
PV = "1.0+git${SRCPV}"
SRCREV = "1934eff09c9ec6741d5573883cb88b230d690a11"

S = "${WORKDIR}/git/aesd-char-driver/"




inherit update-rc.d
INITSCRIPT_PACKAGES = "${PN}"
INITSCRIPT_NAME:${PN} ="aesdchar_start_stop"

INHIBIT_PACKAGE_STRIP="1"




EXTRA_OEMAKE:append_task-install = " -C ${STAGING_KERNEL_DIR} M=${S}/scull"
EXTRA_OEMAKE += "KERNELDIR=${STAGING_KERNEL_DIR}"

do_install () { 
        install -d ${D}/lib/modules/${KERNEL_VERSION}/extra
	   install -m 0755 ${S}/aesdchar.ko ${D}/lib/modules/${KERNEL_VERSION}/extra

	   install -d ${D}${bindir}
	   install -m 0755 ${S}/aesdchar_load ${D}${bindir}
	   install -m 0755 ${S}/aesdchar_unload ${D}${bindir}

	   install -d ${D}${sysconfdir}/init.d
	   install -m 0755 ${S}/aesdchar_start_stop ${D}${sysconfdir}/init.d


}