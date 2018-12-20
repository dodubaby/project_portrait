#!/usr/bin/python3
# coding=utf-8

"""
忽略文件
"""


def ignore(str):
    if str.startswith("."):
        return True
    if str.startswith("__"):
        return True
    if str == "build":
        return True
    if str.endswith("jar"):
        return True
    if str.endswith("zip"):
        return True
    if str.endswith("rar"):
        return True
    else:
        return False


"""
是否是资源文件
"""


def isResourceFile(str):
    if (ignore(str)):
        return False
    # TODO 在此处就确定是资源文件，不去后面确定了，比如他们都在 value 文件夹下面

    if str.end("xml"):
        return True
    else:
        return False


"""
test
"""


def test():
    # print ignore(".ddd")
    print ()


test()
