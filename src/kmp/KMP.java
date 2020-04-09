package kmp;

class kmp {
    public int[] kmp(string inputString, String pattern) {
        int [] result = int[100];
        int next = buildNextTable(pattern);
        int inputStringLength = inputString.length();
        for(int inputIndex = 0, patternIndex = 0; inputIndex < inputStringLength; ++inputIndex) {
            while (patternIndex > 0 && inputString[inputIndex] != pattern[patternIndex]) {
                patternIndex = next[patternIndex];
            }

            if (inputString[inputIndex] == pattern[patternIndex]) {
                ++patternIndex;
            }

            if (patternIndex == pattern.length()) {
                result.push(inputIndex - pattern.length() + 1);
                patternIndex = next[patternIndex];
            }
        }

        return result;
    }

    private int[] buildNextTable(string pattern) {

    }
}