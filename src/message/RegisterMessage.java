package view.message;

import view.Hospital.Register;

public class RegisterMessage {
    private Register register;
    private Register[] registers;

    public RegisterMessage(Register register) {
        this.register = register;
    }

    public RegisterMessage(Register[] registers) {
        this.registers = registers;
    }

    public RegisterMessage() {
    }

    public Register getRegister() {
        return register;
    }

    public Register[] getRegisters() {
        return registers;
    }
}
