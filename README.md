# Java Platform Module System (JPMS)
Groups code tha a higher level. The main purpose of a module is to provide groups of related packages that offer devs a
particular set of functionality

## JAR
Is a Zip file with some extra information

-Module
Is a group of one or more packages plus a special file (module-info.java)

## ----Benefits of Modules----
<ul>
<li>Better access control</li>
<li>Clearer dependency management</li>
<li>Custom Java builds</li>
<li>Improved security</li>
<li>Improved performance</li>
<li>Unique package enforcement</li>
</ul>

    //module-info.java;
    open module zoo.animal.feeding
    {
        export zoo.animal.feeding;
        //All public classes, interfaces, enums and records are exported
        //To be used by Java outside the module
    
        requires zoo.animal.feeding;
        //To indicate that a module is needed
    
        export.zoo.animal.content to zoo.staff;
        //Only module will be able to access to the package
    
        requires transitive zoo.animal.talks;
        //Any module that require this module will also depend on moduleName
    
        opens zoo.animal.talk.media to zoo.staff;
        opens zoo.animal.talk.media;
        //Java allow you to inspect and call code at runtime using reflection
        //open in module: All the packages in the module to be open

       --Java does not allow you to repeat the same module in requires
    }

 - File must be in the root directory of your module (sames as zoo folder)
 - Use module instead of class
 - Following the same name conventions of package



## COMMANDS
### JAVAC

    javac --module-path mods -d feeding staff/zoo/staff/Jobs.java staff/module-info.java  
-p or --module-path location of custom module files  
-d Directory to place class files  

### JAVA

    java -p mods --module zoo.animal.feeding/zoo.animal.feeding.Task
--module or -m : Module / class to run

### JAR

    jar -cvf mods/zoo.animal.care.jar -C care /.

 cvf new jar file  
 -C content to ZIP or JAR

## SERVICES

### Service provider Interface
export api;

Java interface type. It specifies what behavior our service will have

### Service Locator
 export serviceLoader;  
 import api;  
 uses api.interface;  

Can find any classes that implement a service provider interface 

    ServiceLoader<Tour> loader = ServiceLoader.load(Tour.class);

### Service provider
provides api.interface whit serviceProvider.Class;  
requires api;  

    provides zoo.tours.api.Tour with zoo.tours.agency.TourImpl;

Is the implementation of a  service provider interface.

### Consumer
requires api;  
requires serviceLocator;  

    Tour tour =  TourFinder.findSingleTour();

Client refers to a module that obtain and uses a service;  

## Directives
    export package;
    export package to module;
Makes packages available outside the module;

    requires module;
    requires transitive module;
Specifies another module as dependency

    opens package;
    opens package to module;
Allow package to be used with reflection

    provides SerInterface with ImplName;
Makes service provider available

    uses servInterface;
Reference to a service in Service locator

# Built-in Modules

    java.base

Module is imported in all modules by default

    java.*

Modules that you are able to use

    jdk.*

Modules that are specified to the JVM

## Describing a Module
    java -p mods -d zoo.animal.feeding;
    -d or --describe-module
The qualified exports is the full name of the package we are exporting to a specific module

    java --list-modules
List the modules that are available

    java --show-module-resolution -p fedding -m zoo.animal.feeding/zoo.animal.feeding.Task

it is a wat to debugging modules

    jar -f mods/zoo.animal.feeding.jar -d
    --file = -f
    --describe-module = -d

jdeps zoo.dino.jar
jdeps -s zoo.dino.jar
jdeps -summary zoo.dino.jar

Command give you information about dependencies within a module   
to use in projects that are not yet modularized.   
There is also --module-path option if you want to look for modules outside the JDK

    jdeps --jdk-internals zoo.dino.jar
    -jdkinternals = --jdk-internals
Option to provide details about these unsupported APIs

    jlink --module-path mods --add-modules zoo.animal.talk --output zooApp

It is to create a smaller java runtime 

# Types of Modules
## Named Modules
One containing a module-info.java file, this must be in the root folder;
## Automatic Modules
Appears in the module path but does not contain a module-info.java.   
It is simply a regular JAR file that is placed on the module path and gets treated as a module  
- Every JAR file contains a special foleder META-INF/MANIFEST.MF  -  Automatic-Module-Name
- If is not found will short the JAR name
- Automatic modules export all the packages to the other modules
## Unnamed Modules
- Unnamed modules appears on the class path
- Do not export ant packages to name/auto modules
- Work the way Java worked before modules
- Can access to the module path  

# Migration to Module System

## Bottom-Up Migration
- This approach works best when you have the power to convert any JAR files that are not already in modules
- Projects that does not have any dependencies are in the bottom
- Convert the module in the bottom to module path and add requires and exports
- Repeat this process until you have all the modules un the module path

## TOP-DOWN Migration
- When you do not have control of every JAR file used by your app
1. Move all projects to module path
2. Add module-info.java to high project(add export, imports)
3. Repeat

## Big projects to modules
- The wat to do it is to break them into logical groups and then draw the dependencies between them 

*Cycling dependency* - Is when two modules directly or indirectly depend on each other will not compile 



