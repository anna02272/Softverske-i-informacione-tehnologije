%include "io.inc"
section .data
a: dd 0
section .text
global CMAIN
CMAIN:
    GET_DEC 4 , [a]
    mov eax, [a]
    
    cmp eax, 0 
    
    jl negativan
    
    PRINT_STRING "pozitivan"
    NEWLINE
    jmp dalje
    
    negativan:
    PRINT_STRING "negativan"
    NEWLINE
    
    dalje:
    test eax, 1
    je paran
    
    PRINT_STRING "neparan"
    NEWLINE
    jmp kraj
    
    paran:
    PRINT_STRING "paran"
    NEWLINE
    
    kraj:
    xor eax, eax
    ret