#
# Copyright (C) 2015 Wind River Systems, Inc.
#
SUMMARY = "Cobbler is a network-installation server"
DESCRIPTION = "Cobbler is a network-installation server"

LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=ebb5c50ab7cab4baeffba14977030c07"

PR = "r0"

BPV = "2.6.7"
PV = "${BPV}"
SRCREV = "9b3c6d304da00e28fb836d70f3ea6c75f1c70673"

S = "${WORKDIR}/git"

SRC_URI = " \
    git://github.com/cobbler/cobbler.git;branch=release26 \
    "

DEPENDS += " \
        openssl \
        python \
        "

RDEPENDS_${PN} += " \
	apache2 \
	atftp \
	mod-wsgi \
	createrepo \
	python-cheetah \
	python-netaddr \
	python-simplejson \
	python-urlgrabber \
	python-pyyaml \
	rsync \
	genisoimage \
	"

do_compile() {
        oe_runmake
}

do_install() {
        oe_runmake 'DESTDIR=${D}' install
}
