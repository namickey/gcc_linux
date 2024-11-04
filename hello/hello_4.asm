section .text
_start:
    mov rdx, 0xe
    mov rsi, msg
    mov rdi, 0x1
    mov rax, 0x1
    syscall
    mov rdi, 0x0
    mov rax, 0x3c
    syscall
section .rodata
    msg: db "Hello, World!", 0xa