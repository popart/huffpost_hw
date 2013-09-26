#include <stdio.h>
#include <stdlib.h>
#include <string.h>

typedef struct arrayRet {
	int* array;
	int len;
} arrayRet;

arrayRet findConsecutiveRuns(int array[], int len, int runLen);
arrayRet findConsecutiveRuns3(int array[], int len);
void printIntArray(char* name, int input[], int len);

int main() {
	int TEST_LEN = 100;
	int test[TEST_LEN];
	int i;

	//init random input [0,10]
	srand(time(NULL));
	for(i=0; i<TEST_LEN; i++) {
		test[i] = rand()%4; 
	}
	//print input
	printIntArray("input", test, TEST_LEN);
	
	//find indices
	arrayRet x = findConsecutiveRuns3(test, TEST_LEN);
	//print output
	printIntArray("output", x.array, x.len);

	//free malloc'd array
	free(x.array);
	return 0;
}

arrayRet findConsecutiveRuns3(int input[], int len) {
	return findConsecutiveRuns(input, len, 3);
}

//find start indices of 3-number consecutive runs (+/-)
arrayRet findConsecutiveRuns(int input[], int len, int runLen) {
	int *ret = malloc((len-2) * sizeof(int)); //cuz def won't be last two...
	//could also use a flag for (+/-) and just one count
	//would have to set the first value...
	int countUp = 1;
	int countDown = 1;

	int head = input[0]; //currentVal
	int retI = 0; //index of return

	int diff; 
	int i; //index of iterator 
	for(i=1; i<len-2; i++) {
		diff = input[i]-head;
		head = input[i];
		if(diff == 1 ) { // +1
			if(++countUp >= runLen) {
				countUp = runLen; countDown = 1;
				ret[retI] = i-(runLen-1);
				retI++; //if anything found, retI > 0
			}
		}
		if(diff == -1) { //-1
			if(++countDown >= runLen) {
				countDown = runLen; countUp = 1;
				ret[retI] = i-(runLen-1);
				retI++; 
			}
		}
		else {
			countUp = 1; countDown = 1;
		}
	}
	arrayRet ar;
	ar.array = ret;
	ar.len = retI; //stays 0 if nothing found

	return ar;
}

void printIntArray(char* name, int input[], int len) {
	int i;

	printf("%s: [", name);
	for(i=0; i<len; i++) {
		if(i < len-1) printf("%d, ", input[i]); //comma
		else printf("%d", input[i]); //no comma at end
	}
	printf("]\n");
}
