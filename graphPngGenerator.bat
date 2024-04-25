@echo off
setlocal enabledelayedexpansion

set base_name=graph_image
set extension=.png
set counter=1

:check_file_existence
if exist MATDISC_graph_images\%base_name%%counter%%extension% (
    set /a counter+=1
    goto check_file_existence
)

dot -Tpng MATDISC_graph_images\graph.dot -o MATDISC_graph_images\%base_name%%counter%%extension%
