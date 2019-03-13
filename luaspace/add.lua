function add(...)
	local s=0
	for i,val in ipairs{...} do
	s=s+val
	end
	print(select("#",...))
	return s
	end
print(add(1,2,3,4,5,6))
