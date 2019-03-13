function maximun(a)
	local mi=1
	local m=a[mi]
	for i,val in ipairs(a) do
	if val>m then
	mi=i
	m=val
	end
	end
	return m,mi
	end
print(maximun({8,10,23,12,5}))
