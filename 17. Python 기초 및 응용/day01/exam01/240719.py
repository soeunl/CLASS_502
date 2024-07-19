# for i in range(3, 10):
#     print("%d회 반복" % (i + 1))

# for i in range(2, 10): # 2~9
#     print("-----%d단-----" % i)
#     for j in range(1, 10): # 1~9
#         print("%d X %d = %d" % (i, j, i * j))

# print("ABC", end = '')
# print("DEF")
# print('', end='\n')
# print("가나다")

# nums = [10, 20, 30, 40, 50]
# newNums = []
# for num in nums:
#     newNums.append(num * num)
# print(newNums)

# nums = [10, 20, 30, 40, 50]
# newNums = [num * num for num in nums]
# print(newNums)

# nums = [i for i in range(1, 51)]
# print(nums)

# nums = [i for i in range(1, 51)]
# print(nums)
#
# nums2 = [i * 2 for i in range(1, 51) if i % 2 == 0]
# print(nums2)

# gugudan = ["%d X %d = %d" % (i, j, i * j) for i in range(2, 10)
#            for j in range(1, 10)]
# for s in gugudan:
#     print(s)

def add(a, b):
    return a + b
result = add(10, 20)
print(result)

def minus(a, b = 10):
    return a - b
# minus(10, 20)
# minus(a=10, b=20)
# minus(b=20, a=10)
result2 = minus(30)
print(result2)