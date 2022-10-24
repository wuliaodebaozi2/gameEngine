# For more information about using CMake with Android Studio, read the
# documentation: https://d.android.com/studio/projects/add-native-code.html

# Sets the minimum version of CMake required to build the native library.

cmake_minimum_required(VERSION 3.18.1)


##官方标准配置
set(CMAKE_C_FLAGS "${CMAKE_C_FLAGS} -Wall")
set(CMAKE_CXX_FLAGS "${CMAKE_CXX_FLAGS} -std=c++11 -fno-rtti -fno-exceptions -Wall")

##ANDROID_PLATFORM_LEVEL=18
add_definitions("-DDYNAMIC_ES3")
set(OPENGL_LIB GLESv2)
# Declares and names the project.

project("bulletopenglesBak")

# Creates and names a library, sets it as either STATIC
# or SHARED, and provides the relative paths to its source code.
# You can define multiple libraries, and CMake builds them for you.
# Gradle automatically packages shared libraries with your APK.

add_library( # Sets the name of the library.
        bulletopenglesBak

        # Sets the library as a shared library.
        SHARED

        # Provides a relative path to your source file(s).
        native-lib.cpp)

message("======================================")
#当前的abi 信息
message(WARNING "4. WARNING  ${ANDROID_ABI}")
#cmake 所在的目录
message(WARNING "2. PROJECT_SOURCE _DIR = ${CMAKE_SOURCE_DIR}")

target_include_directories(bulletopenglesBak
        PRIVATE
        ${CMAKE_SOURCE_DIR}/../include)


##导入第三方库，不同的库需要分开导入
add_library(libBullet STATIC  IMPORTED)
set_target_properties(libBullet PROPERTIES IMPORTED_LOCATION ${CMAKE_SOURCE_DIR}/../jniLibs/${ANDROID_ABI}/libBullet.a)

# Searches for a specified prebuilt library and stores the path as a
# variable. Because CMake includes system libraries in the search path by
# default, you only need to specify the name of the public NDK library
# you want to add. CMake verifies that the library exists before
# completing its build.

find_library( # Sets the name of the path variable.
        log-lib

        # Specifies the name of the NDK library that
        # you want CMake to locate.
        log)

# Specifies libraries CMake should link to your target library. You
# can link multiple libraries, such as libraries you define in this
# build script, prebuilt third-party libraries, or system libraries.

target_link_libraries( # Specifies the target library.
        bulletopenglesBak

        # Links the target library to the log library
        # included in the NDK.
        ${log-lib})

target_link_libraries( # Specifies the target library.
        bulletopenglesBak
        libBullet
        )

add_library(gltest-lib
        SHARED
        RenderJNI.cpp)

target_include_directories(gltest-lib
        PRIVATE
        ${CMAKE_SOURCE_DIR}/../include)

target_link_libraries(gltest-lib
        ${OPENGL_LIB}
        android
        EGL
        log
        m
        libBullet)