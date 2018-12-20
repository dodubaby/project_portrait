# encoding: utf-8
#!/usr/bin/python3

import sys
from sys import argv, path

print('========== start ============')
for i in sys.argv:
    print(i + '\n')
print('\n路径为：\n', sys.path)

print('argv:', argv)
print('path:', path)

a = 1
b = 0.1
c = 4 + 4j
print(type(a))
print(type(b))
print(type(c))

print(isinstance(a, int))
print(isinstance(b, int))

a = [1, 2, 1, 4, 1, 'wenzi', 2, 22222]
print(a[2: -1])
print(a[:])
print(a[-3:-1])
print(a[-3:-1][0:1])

print(a[:])
a[1:2] = [666, 777, 2]
print(a[:])

b = (1, 2, 3, 4)
print(b[1:3])

print('------------')
set1 = {'11', '22', '33'}
set2 = {'11', '22', '111111', '111111'}
print(set1)
print(set2)

if '11' in set1:
    print('在')
else:
    print('不在')

print(set1 - set2)

print('------------')
dict1 = {}
dict1['11'] = '数字1'
dict1[2] = '数字2'
print(dict1[2])
print(dict1['11'])
print('------------')
print(type(str(2)))
