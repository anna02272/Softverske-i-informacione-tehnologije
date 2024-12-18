%include "io.inc"

section .data
a: dd 0 
b: dd 0

section .text
global CMAIN
CMAIN:
    
    GET_DEC 4, [a]
    GET_DEC 4, [b]
    
    mov eax, [a]
    mov ebx, [b]
    mov ecx, eax
    mov edx, ebx
    
    PRINT_DEC 4, eax
    NEWLINE
    PRINT_DEC 4, ebx
    NEWLINE
    PRINT_DEC 4, ecx
    NEWLINE
    PRINT_DEC 4, edx
    
    xor eax, eax
    ret