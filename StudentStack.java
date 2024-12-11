public class StudentStack {

        private Student[] stack;
        private int top;

        // Constructor to initialize the stack with a fixed size
        public StudentStack(int capacity) {
            stack = new Student[capacity];
            top = -1;  // Empty stack
        }

        // Push a student to the stack
        public void push(Student student) {
            if (top < stack.length - 1) {
                stack[++top] = student;
            } else {
                System.out.println("Stack is full.");
            }
        }

        // Pop a student from the stack
        public Student pop() {
            if (top >= 0) {
                return stack[top--];
            } else {
                System.out.println("Stack is empty.");
                return null;
            }
        }

        // Peek the top student without removing
        public Student peek() {
            if (top >= 0) {
                return stack[top];
            } else {
                System.out.println("Stack is empty.");
                return null;
            }
        }

        // Check if the stack is empty
        public boolean isEmpty() {
            return top == -1;
        }

        // Get the number of elements in the stack
        public int size() {
            return top + 1;
        }
    }


