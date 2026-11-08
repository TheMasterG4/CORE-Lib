cmake_minimum_required(VERSION 4.0)
project(GeraldsCore C)

set(CMAKE_C_STANDARD 23)

find_package(JNI REQUIRED)
include_directories(${JNI_INCLUDE_DIRS})
link_libraries(${JNI_LIBRARIES})
set(CMAKE_LIBRARY_OUTPUT_DIRECTORY "C:\\Users\\Boris\\Desktop\the_files\\libraries\\core\\GeraldsCore\\src\\native")
add_library(GeraldsCore SHARED
        library.c
        src/image/colors/EasyColor.c
)
