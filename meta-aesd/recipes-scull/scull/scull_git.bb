# Recipe created by recipetool
# This is the basis of a recipe and may need further editing in order to be fully functional.
# (Feel free to remove these comments when editing.)

# WARNING: the following LICENSE and LIC_FILES_CHKSUM values are best guesses - it is
# your responsibility to verify that the values are complete and correct.
#
# The following license files were not able to be identified and are
# represented as "Unknown" below, you will need to check them yourself:
#   LICENSE
LICENSE = "Unknown"
LIC_FILES_CHKSUM = "file://LICENSE;md5=f098732a73b5f6f3430472f5b094ffdb"

SRC_URI = "git://git@github.com/cu-ecen-aeld/assignment-7-polandru.git;protocol=ssh;branch=main"

inherit module
FILES:${PN} += "${sysconfdir}/init.d/scull_start_stop"
FILES:${PN} += "${bindir}/scull_load"
FILES:${PN} += "${bindir}/scull_unload"

	 
	       



# Modify these as desired
PV = "1.0+git${SRCPV}"
SRCREV = "3a9e1b41cc75c658412e7ab7d19a02c4b365c133"

S = "${WORKDIR}/git"




inherit update-rc.d
INITSCRIPT_PACKAGES = "${PN}"
INITSCRIPT_NAME:${PN} ="scull_start_stop"

INHIBIT_PACKAGE_STRIP="1"




EXTRA_OEMAKE:append_task-install = " -C ${STAGING_KERNEL_DIR} M=${S}/scull"
EXTRA_OEMAKE += "KERNELDIR=${STAGING_KERNEL_DIR}"

do_install () { 
        install -d ${D}/lib/modules/${KERNEL_VERSION}/extra
	   install -m 0755 ${S}/scull/scull.ko ${D}/lib/modules/${KERNEL_VERSION}/extra

	   install -d ${D}${bindir}
	   install -m 0755 ${S}/scull/scull_load ${D}${bindir}
	   install -m 0755 ${S}/scull/scull_unload ${D}${bindir}

	   install -d ${D}${sysconfdir}/init.d
	   install -m 0755 ${S}/scull/scull_start_stop ${D}${sysconfdir}/init.d


}