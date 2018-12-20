# encoding: utf-8
# !/usr/bin/python3
import os

from db import db
from fileCheck import ignore
import util.fileUtil  as fileUtil
import util.timeUtil as timeUtil
import regular.regular as regular

"""
解析文件列表
"""


def analysisFileList(path):
    files = fileUtil.readFileList(path)
    for file in files:  # 遍历文件夹
        # 忽略文件
        if (ignore(file)):
            continue
        # 解析文件/文件夹
        fullName = path + "/" + file
        if fileUtil.isDir(fullName):
            print "++++++ File: " + fullName
            print "++++++ File: " + "dir"
            # 保存数据
            db.saveFileData("dir", fullName, path, file, "", "", "", "")
            # 继续遍历文件夹
            analysisFileList(fullName)
        elif fileUtil.isFile(fullName):
            fileSuffix = os.path.splitext(file)[-1][1:]
            classFullName = ''
            fileSize = fileUtil.getSize(fullName)
            lineCount = fileUtil.getLineCount(fullName)
            # java文件，单独生成类名
            if ("java" == fileSuffix):
                classFullName = regular.regularFileJava(fullName)
                classFullName = classFullName.replace('/', '.').replace('java', '')
            print "------ File: " + fullName
            print "------ File: 大小" + str(fileSize) + " | 行数" + str(
                lineCount) + " | " + file + " | " + fileSuffix + " | " + classFullName
            # 保存数据
            db.saveFileData("file", fullName, path, file, fileSuffix, classFullName, lineCount, fileSize)
        else:
            print "xxxxxx File: error! " + fullName
            print "xxxxxx File: error! "


"""
test
"""


def test():
    time = timeUtil.timeStart()
    analysisFileList("../../../")
    timeUtil.timeEnd(time)


test()
