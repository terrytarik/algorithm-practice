import re
import doctest


def search_min_count(binary_number, number):
    # """
    # >>> search_min_count('101101101', 5)
    # 3
    # >>> search_min_count('1111101', 5)
    # 1
    # >>> search_min_count('100111011110100100111110110011100101000111100101110010001100111011110100100111110110011100101000110010110000111100101110010001', 7)
    # 5
    # >>> search_min_count('110011011', 5)
    # 3
    # """
    powers = ['1']
    minimal_count = 0

    for i in range(len(binary_number)):
        num_in_power = bin(number ** (i + 1))[2:]
        if len(num_in_power) > len(binary_number):
            break
        powers.append(num_in_power)
    powers.reverse()

    for power in powers:
        result_number, re_count = re.subn(power, '', binary_number)
        binary_number = result_number
        minimal_count += re_count
    if len(binary_number) != 0:
        return -1
    return minimal_count


if __name__ == '__main__':
    print(search_min_count('101101101', 11))
    doctest.testmod(verbose=True)
