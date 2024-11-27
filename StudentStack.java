package Asm_1;

public class StudentStack {
    private Student[] stack;
    private int top;

    public StudentStack(int capacity) {
        stack = new Student[capacity];
        top = -1;
    }

    public void push(Student student) {
        if (top == stack.length - 1) {
            System.out.println("Stack overflow. Expanding stack size...");
            expandCapacity();
        }
        stack[++top] = student;
    }

    public Student pop() {
        if (isEmpty()) {
            System.out.println("Stack underflow.");
            return null;
        }
        return stack[top--];
    }

    public Student peek() {
        if (isEmpty()) {
            System.out.println("Stack is empty.");
            return null;
        }
        return stack[top];
    }

    public boolean isEmpty() {
        return top == -1;
    }

    private void expandCapacity() {
        Student[] newStack = new Student[stack.length * 2];
        System.arraycopy(stack, 0, newStack, 0, stack.length);
        stack = newStack;
    }

    public void displayStack() {
        if (isEmpty()) {
            System.out.println("Stack is empty.");
            return;
        }
        System.out.println("--- Student Stack ---");
        for (int i = 0; i <= top; i++) {
            System.out.println(stack[i]);
        }
    }
}
