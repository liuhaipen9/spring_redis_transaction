--[[ 函数返回两个值的最大值 --]]
function max(num1,num2)
   	if(num1 > num2)then
		result=num1;
	else
             result=num2;
	end
	return result;
	end
  print("两值比较最大的值是:",max(10,4))
print("两值比较最大的值是:",max(5,6))
