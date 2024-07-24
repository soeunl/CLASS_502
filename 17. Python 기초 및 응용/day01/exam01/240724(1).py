f = open("D:/test1.txt", "w")
for i in range(1, 11):
    f.write("%d행 입력\n" % (i + 1))
f.close()
