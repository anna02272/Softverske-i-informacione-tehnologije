%include "io.inc"

section .data
a: dd 0 
b: dd 0
c: dd 0
d: dd 0
e: dd 0 
r: dd 0

section .text
global CMAIN
CMAIN:
    mov ebp, esp; for correct debugging

    GET_DEC 4, [a]
    GET_DEC 4, [b]
    GET_DEC 4, [c]
    GET_DEC 4, [d]
    GET_DEC 4, [e]
    
    mov eax, [a]
    mov ebx, [b]
    mov ecx, 2
    mul ebx
    mul ecx
    
    mov ebx, eax
    mov eax, [c]
    mov ecx, [d]
    mov edx, 0
    div ecx
    
    add ebx, eax
    
    mov eax, [e]
    mov ecx, 2
    mov edx, 0
    div ecx
    sub ebx, eax
    
    PRINT_DEC 4, ebx
    NEWLINE
    
    mov eax, ebx
    mov ebx, 2
    mov edx, 0
    div ebx
    
    PRINT_DEC 4, edx
    NEWLINE
    
    xor eax, eax
    ret