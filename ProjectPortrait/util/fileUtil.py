#!/usr/bin/python3
# coding=utf-8

import os

"""
写文件
"""


def writeFile(filePath, content, isAppend):
    mode = 'w'
    if isAppend:
        mode = 'a+'
    else:
        mode = 'w'
    with open(filePath, mode) as f:
        f.write(content)
        f.write('\n')


"""
读取文件列表
"""


def readFileList(path):
    files = os.listdir(path)  # 得到文件夹下的所有文件名称
    return files


"""
读取文件内容
"""


def readFileContent(file):
    f = open(file)  # 打开文件
    iter_f = iter(f)  # 创建迭代器
    return iter_f


"""
是否是文件夹
"""


def isDir(file):
    return os.path.isdir(file)


"""
是否是文件
"""


def isFile(file):
    return os.path.isfile(file)


"""
获取大小
"""


def getSize(file):
    return str(os.path.getsize(file) / 1024)


"""
获取行数
"""


def getLineCount(file):
    return len(open(file, 'r').readlines())


"""
test
"""


def test():
    # first = []
    # second = []
    # with open('../data/mergeText.txt', 'w') as f:
    #     with open('../data/1.txt', 'r') as f1:
    #         for line in f1:
    #             line = line.strip()
    #             first.append(line)
    #     with open('../data/2.txt', 'r') as f2:
    #         for line2 in f2:
    #             line2 = line2.strip()
    #             second.append(line2)
    #
    #     for i in range(0, 1):
    #         result = first[i] + '\t' + second[i] + '\n'
    #         f.write(result)

    # writeFile('../data/平谷.txt', '平谷区', 'true')
    # test()

    readFileList("../../../")


test()
