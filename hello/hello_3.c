#include <sys/syscall.h>
#include <unistd.h>
int main() {
    syscall(SYS_write, 1, "Hello, World!!!\n", 16);
}