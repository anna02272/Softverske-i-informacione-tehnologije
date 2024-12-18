%include "io.inc"
section .data
a:dd 00
b:dd 00
maximum: dd 0

section .text
global CMAIN
CMAIN:
    GET_DEC 4,[a]
    GET_DEC 4,[b]
    
    mov eax, [a]
    mov ebx,[b]
    
    cmp eax,ebx
    jg veceodnule
    mov [maximum], ebx
    jmp rezultat
    
    veceodnule:
    mov [maximum],eax
    
    rezultat:
    PRINT_DEC 4,[maximum]
    NEWLINE
    
    xor eax, eax
    ret