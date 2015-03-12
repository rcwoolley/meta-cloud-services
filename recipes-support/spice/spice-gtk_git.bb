#
# Copyright (C) 2015 Wind River Systems, Inc.
#

SUMMARY = "A GTK+ widget for SPICE clients"
DESCRIPTION = "Client libraries for SPICE"

LICENSE = "LGPLv2.1+"
LIC_FILES_CHKSUM = "file://COPYING;md5=4fbd65380cdd255951079008b364516c"

PR = "r0"
PV = "0.20"

# Actual versions based on the checkouts below
# spice-gtk = "0.20"
# common = "0.12.6"
# protocol = "0.12.6"
SRCREV_spice-gtk = "d592c4de3eef656a8a7e400c7e82911856de7023"
SRCREV_spice-common = "fe93908238196bd632287fc9875e6f2e11105d04"
SRCREV_spice-protocol = "784407f248e7f99d2bfcc9368f9acd1efb2b9617"

SRC_URI = "git://anongit.freedesktop.org/spice/spice-gtk;name=spice-gtk \
           git://anongit.freedesktop.org/spice/spice-common;destsuffix=git/spice-common;name=spice-common \
           git://anongit.freedesktop.org/spice/spice-protocol;destsuffix=git/spice-common/spice-protocol;name=spice-protocol \
          "

SRC_URI += " \
        file://make_gtk-doc_optional.patch \
        file://configure.ac-add-subdir-objects-to-AM_INIT_AUTOMAKE.patch \
        file://build-allow-separated-src-and-build-dirs.patch \
        "
S = "${WORKDIR}/git"

inherit autotools gettext pythonnative python-dir pkgconfig

PACKAGECONFIG ?= "sasl"

PACKAGECONFIG[smartcard] = "--enable-smartcard,--disable-smartcard,libcacard,"
PACKAGECONFIG[sasl] = "--with-sasl,--without-sasl,cyrus-sasl,"

DEPENDS += " python-native celt051 python-pyparsing libjpeg-turbo pixman alsa-lib glib-2.0"
DEPENDS += " intltool gtk+ libusb1 openssl pulseaudio python-pygtk zlib python-pyparsing-native"
DEPENDS += "libtext-csv-perl-native"
# DEPENDS += " gtk-doc-stub"
# usbredir libgudev libcacard

EXTRA_OECONF = "--enable-maintainer-mode --disable-gtk-doc --with-gtk=3.0 --enable-vala"

do_configure_prepend() {
	mkdir -p ${S}/spice-common/spice-protocol/m4
}


do_install_append() {
	cd ${B}/spice-common/spice-protocol
	oe_runmake DESTDIR="${D}" install
	cd -

	rm ${D}${includedir}/spice-1/spice/*
	rmdir ${D}${includedir}/spice-1/spice
	rmdir ${D}${includedir}/spice-1

	rm ${D}${datadir}/pkgconfig/spice-protocol.pc
	rmdir ${D}${datadir}/pkgconfig

	rm ${D}${datadir}/vala/vapi/spice-protocol.vapi
	rmdir ${D}${datadir}/vala/vapi
	rmdir ${D}${datadir}/vala
}
