@echo off
rem ---------------------------------------------------------------------------
rem SiteTester.bat - Start Script for the SiteTester Application
rem
rem Environment Variable Prequisites:
rem
rem
rem   JAVA_HOME     	Must point at your Java Development Kit installation.
rem
rem ---------------------------------------------------------------------------

SET JAVA_HOME="C:\Program Files\Java\jre1.8.0_25"

rem FOP class path only required for PDF export
SET FOP_CP=.lib\fop\avalon-framework-4.2.0.jar;.lib\fop\batik-all-1.7.jar;.lib\fop\commons-io-1.3.1.jar;.lib\fop\commons-logging-1.0.4.jar;.lib\fop\fop.jar;.lib\fop\serializer-2.7.0.jar
SET FOP_CP=%FOP_CP%;.lib\fop\servlet-2.2.jar;.lib\fop\xalan-2.7.0.jar;.lib\fop\xercesImpl-2.7.1.jar;.lib\fop\xml-apis-1.3.04.jar;.lib\fop\xml-apis-ext-1.3.04.jar;.lib\fop\xmlgraphics-commons-1.5.jar

rem required libraries
SET LIB_CP=.\lib\kahve_0_8_2.jar;.\lib\binding-1.3.1.jar;.\lib\commons-codec-1.3.jar;.\lib\irunj_115.jar;.\lib\jgoodies-forms-1.4.0.jar;.\lib\jgoodies-common-1.6.0.jar;.\lib\jai_codec.jar

rem extra libraries (for equation, svg, spell checker and lookandfeel)
SET EXT_CP=%LIB_CP%;.\lib\jmath-1.0.3.jar;.\lib\jmath-fonts.jar;.\lib\svgSalamander-tiny.jar;.\lib\dict_en.jar;.\lib\jmyspell-core-1.0.0-beta-2.jar;.\lib\jgoodies-looks-2.5.3.jar;

SET CLASS_PATH=.\jword-3.4.5.jar;%LIB_CP%;%EXT_CP%;%FOP_CP%;
%JAVA_HOME%\bin\java -Dsun.java2d.noddraw=true -cp %CLASS_PATH% com.pilot.jword.demo.DemoUI -spell -equation -pdf