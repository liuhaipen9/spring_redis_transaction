array={"Lua","Tutorial"}

function elementIterator( collection )
	-- body
	local index = 0
	local count = #collection
	print("长度",count)
	--闭包函数
	return function (  )
	index=index+1
	if
		index<=count
		then
		--返回迭代器的当前元素
		return collection[index]
	end
	end
end

for element in elementIterator(array)do
	print(element)
end
