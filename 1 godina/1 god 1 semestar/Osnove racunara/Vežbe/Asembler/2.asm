%include "io.inc"
section .data

section .text
global CMAIN
CMAIN:
    
    mov ebp, esp
    mov eax, 5
    mov ebx, 8
    mov ecx, eax
    mov edx, ebx

    xor eax, eax
    ret