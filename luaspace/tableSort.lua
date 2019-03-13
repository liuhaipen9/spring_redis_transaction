fruits={"banana","orange","apple","grapes"}
print("排序前")
for k,v in ipairs(fruits) do
	print(k,v)

end
print("排序后")
table.sort(fruits)
for k,v in ipairs(fruits) do
	print(k,v)
end
