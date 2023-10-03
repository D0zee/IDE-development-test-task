# Counter Tool plugin
It's realization test task on IDE development thesis. This plugin shows count of functions and classes
for each kotlin file which is placed in the project. If you want to see the up-to-date statistic after changes
you should click on `Update Statistic` button.  

In plugin there are three columns: relative path (project path as base), count of classes, count of functions. It counts ALL functions and ALL classes in the file.

There is another way of realization this plugin. For example, we can listen updates from some instances which change PSI in real-time and change only concrete statistic of concrete file. 
All depends on applying and ways of using this plugin. I know how to do it, but my plugin saves more resources than a such plugin with real-time updating.  
