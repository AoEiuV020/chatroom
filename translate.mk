#	***************************************************
#		^> File Name: java.mk
#		^> Author: AoEiuV020
#		^> Mail: 490674483@qq.com
#		^> Created Time: 2016/01/19 - 22:54:49
#	***************************************************
name=Main.java
package=chatroom.translate.
path=$(subst .,/,$(package))
n=$(path)$(name)
bn=$(package)$(basename $(name))
JAVACENCODING="-Dfile.encoding=utf8"
JAVAENCODING="-Dfile.encoding=utf8"
LANG="-Duser.country=US"
JAVACFLAG=-J$(JAVACENCODING) -J$(LANG)
JAVAFLAG=$(JAVACENCODING) $(LANG)
jarpath=libs/
mysql=$(jarpath)mysql.jar
json=$(jarpath)json-lib-2.4-jdk15.jar
beanutils=$(jarpath)commons-beanutils-1.9.2.jar
logging=$(jarpath)commons-logging-1.2.jar
lang=$(jarpath)commons-lang-2.6.jar
collections=$(jarpath)commons-collections-3.2.2.jar
ezmorph=$(jarpath)ezmorph-1.0.6.jar
jar=-classpath .:$(mysql):$(json):$(beanutils):$(collections):$(lang):$(logging):$(ezmorph):
JAVACFLAG+=$(jar)
JAVAFLAG+=$(jar)
args=0.0.0.0 45678
ECHO=echo $@:$? done...
all:javac java
	@$(ECHO)

javac:clean
	javac $(JAVACFLAG) $(n)

java:
	java $(JAVAFLAG) $(bn) $(args)

chname:
	sed -i "s/\<$(name)\>/$(N)/" makefile
clean:
	@-find . -name "*.class" -exec rm {} \;
