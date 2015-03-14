# Disclaimer #

This framework is not production ready. It might be in the not too far future, but right now, there are too many pages / components not yet worked on. So please, be aware that you **will** run into problems. Also you'll stumble over ugly HTML, early hacks, whatever ugly stuff that will change in the future.

**This project is in active development - it will change, it will break sometimes. You are warned!**

# Introduction #

EVDirectToWeb is just a look frameworks like the ones from Apple or ERNeutralLook from Wonder. You can link to the framework and the rules flie in there will configure your application to use the component from this framework.

To get started, check out the framework with Eclipse into your workspace, it should end up as a project called "EVDirectToWeb" and should be build automatically by the WOLips incremental builder. The framework requires several projects to be in your workspace:

  * Ajax
  * ERExtensions
  * ERJars
  * ERDirectToWeb
  * JavaWOExtensions
  * WOOgnl

All these frameworks are included in [Project Wonder](http://wonder.sourceforge.net) and you should be comfortable with using them.

You have to switch on the WOOgnl template parser as a couple of the components use a mix of traditional style and the new inline style bindings. I use the following:

```
ognl.inlineBindings=true
ognl.active = true
ognl.helperFunctions=true
ognl.parseStandardTags=true
```

The components will get cleaned up to either use one or the other style in the future, this is just not done yet.

To get the look work correctly, you also have to include the provided css file. Best is to add a softlink to your webserver root folder pointing to the "Web" folder in this framework. On Mac OS X you do this by executing this command:

```
ln -s /Users/yourname/Projects/workspace/EVDirecctToWeb/Web d2w
```

Make sure, all folders on the path to the project are readable / executable either by you (should be ;-)) and / or the webserver if you don't use direct connect. Apache won't follow a path where one directory is not accessible.

I will make my own toy application available as an open source project once, I think I can show it to some people without being ashamed ... ;-) Than you have examples on how I got things working.  As this will probably take some time, I'm sometimes on this irc channel:

#webo on irc.freenode.net

I'll try to help but please understand, that I don't have the time to give real support to use the framework.