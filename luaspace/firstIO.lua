file=io.open("test.lua","r")

io.input(file)

print(io.read())

io.close(file)

file=io.open("test.lua","a")

io.output(file)

io.write("-- test.lua 文件末尾注释")

--关闭打开的文件

io.close(file)