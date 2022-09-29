<!-- By Ross Crowley -->
function answer() {
	var c=0;
	var q1=document.quiz.question1.value;
	var q2=document.quiz.question2.value;
	var q3=document.quiz.question3.value;
	var q4=document.quiz.question4.value;
	var q5=document.quiz.question5.value;
	var q6=document.quiz.question6.value;
	var q7=document.quiz.question7.value;
	var q8=document.quiz.question8.value;
	var q9=document.quiz.question9.value;
	var q10=document.quiz.question10.value;
	var result=document.getElementById('result');
	var quiz=document.getElementById('quiz');
	 
	if (q1=="Michael Phelps") {c++} 
	if (q2=="Brazil") {c++}
	if (q3=="Basketball") {c++}
	if (q4=="South Africa") {c++}
	if (q5=="Darts") {c++}
	if (q6=="2003") {c++}
	if (q7=="7") {c++}
	if (q8=="Johnny Sexton") {c++}
	if (q9=="Joe Fraizer") {c++}
	if (q10=="22") {c++}
		
		quiz.style.display="none";
		
		
		if (c<=3) {
			result.textContent=`Your result is ${c}. That is not very good!!`
		} else if (c<=7){
			result.textContent=`Your result is ${c}. it is good but you can improve.`
		} else if (c>7){
			result.textContent=`Your result is ${c}. That is excellent. Well done!`
		}
}