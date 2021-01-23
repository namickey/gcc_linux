section .data
    mytext: db "hello world.", 0x0a

section .text
    global _start

_start:
    mov rax, 1
    mov rdi, 1
    mov rsi, mytext
    mov rdx, 13
    syscall

    mov rax, 60
    mov rdi, 1
    syscall
