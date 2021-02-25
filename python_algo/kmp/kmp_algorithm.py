def get_prefix_function(pattern: str):
    string_len = len(pattern)
    prefix_func = [0 for _ in range(string_len)]
    first_symbol_iterator = 0
    second_symbol_iterator = 1

    while second_symbol_iterator < string_len:

        if pattern[first_symbol_iterator] == pattern[second_symbol_iterator]:
            prefix_func[second_symbol_iterator] = first_symbol_iterator + 1
            first_symbol_iterator += 1
            second_symbol_iterator += 1

        elif first_symbol_iterator > 0:
            first_symbol_iterator = prefix_func[first_symbol_iterator - 1]

        else:
            prefix_func[second_symbol_iterator] = 0
            second_symbol_iterator += 1

    return prefix_func


def kmp_algorithm(text: str, pattern: str):
    if (type(text) is not str) or (type(pattern) is not str) or \
            (len(text) <= 0) or (len(pattern) <= 0) or (len(pattern) > len(text)):
        raise ValueError("Incorrect data")

    pattern_len = len(pattern)
    text_len = len(text)
    matches = []
    text_iterator = 0
    pattern_iterator = 0
    prefix_func = get_prefix_function(pattern)

    while text_iterator < text_len and pattern_iterator < pattern_len:

        if text[text_iterator] == pattern[pattern_iterator]:
            pattern_iterator += 1
            text_iterator += 1

        if pattern_iterator == pattern_len:
            matches.append((text_iterator - pattern_len, text_iterator - 1))
            pattern_iterator = prefix_func[pattern_iterator - 1]

        elif text[text_iterator] != pattern[pattern_iterator] and text_iterator < text_len:

            if pattern_iterator == 0:
                text_iterator += 1

            else:
                pattern_iterator = prefix_func[pattern_iterator - 1]

    return matches

