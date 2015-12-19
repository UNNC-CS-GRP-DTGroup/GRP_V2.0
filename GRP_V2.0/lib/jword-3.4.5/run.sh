FOP_CP=.lib/fop/avalon-framework-4.2.0.jar:.lib/fop/batik-all-1.7.jar:.lib/fop/commons-io-1.3.1.jar:.lib/fop/commons-logging-1.0.4.jar:.lib/fop/fop.jar:.lib/fop/serializer-2.7.0.jar

FOP_CP=$FOP_CP:.lib/fop/servlet-2.2.jar:.lib/fop/xalan-2.7.0.jar:.lib/fop/xercesImpl-2.7.1.jar:.lib/fop/xml-apis-1.3.04.jar:.lib/fop/xml-apis-ext-1.3.04.jar:.lib/fop/xmlgraphics-commons-1.5.jar

LIB_CP=./lib/kahve_0_8_2.jar:./lib/binding-1.3.1.jar:./lib/commons-codec-1.3.jar:./lib/irunj_115.jar:./lib/jgoodies-forms-1.4.0.jar:./lib/jgoodies-common-1.6.0.jar:./lib/jai_codec.jar

EXT_CP=$LIB_CP:./lib/jmath-1.0.2.jar:./lib/jmath-fonts.jar:./lib/svgSalamander-tiny.jar:./lib/dict_en.jar:./lib/jmyspell-core-1.0.0-beta-2.jar:./lib/jgoodies-looks-2.5.3.jar:

CLASS_PATH=./jword-3.4.5.jar:$LIB_CP:$EXT_CP:$FOP_CP:

java -Dsun.java2d.noddraw=true -cp $CLASS_PATH com.pilot.jword.demo.DemoUI -spell -equation -pdf