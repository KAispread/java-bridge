package bridge.input;

import bridge.controller.GameCommand;
import bridge.controller.MoveCommand;

import java.util.List;
import java.util.regex.Pattern;

import static bridge.controller.BridgeSize.MAXIMUM;
import static bridge.controller.BridgeSize.MINIMUM;

public class BasicBridgeValidator implements BridgeValidator {
    MoveCommand[] moveCommands = MoveCommand.values();
    GameCommand[] gameCommands = GameCommand.values();

    @Override
    public void validateBridgeSize(String size) throws IllegalArgumentException {
        validateInteger(size);
        validateSizeRange(size);
    }

    @Override
    public void validateMoveCommand(String command) throws IllegalArgumentException {
        validateStringMoveCommand(command);
    }

    @Override
    public void validateGameCommand(String command) throws IllegalArgumentException {
        validateStringGameCommand(command);
    }

    @Override
    public void validateBridge(List<String> bridge) throws IllegalArgumentException {
        validateBridgeSize(String.valueOf(bridge.size()));
        for (String command : bridge) {
            if (!Pattern.matches("^[UD]$", command)) {
                String errMessage = "[ERROR] 다리는 U, D만 포함하고 있어야 합니다.";
                System.out.println(errMessage);
                throw new IllegalArgumentException();
            }
        }
    }

    private void validateInteger(String size) {
        if (!Pattern.matches("^[0-9]*$", size)) {
            String errMessage = "[ERROR] 다리 길이는 자연수만 입력 가능합니다.";
            System.out.println(errMessage);
            throw new IllegalArgumentException();
        }
    }

    private void validateSizeRange(String size) {
        int sizeValue = Integer.parseInt(size);
        if (sizeValue < MINIMUM.getSize() | sizeValue > MAXIMUM.getSize()) {
            String errMessage = String.format("[ERROR] 다리 길이는 %d 이상 %d 이하만 가능합니다.", MINIMUM.getSize(), MAXIMUM.getSize());
            System.out.println(errMessage);
            throw new IllegalArgumentException();
        }
    }

    private void validateStringMoveCommand(String command) {
        for (MoveCommand moveCommand : moveCommands) {
            if (command.equals(moveCommand.getCommand())) {
                return;
            }
        }
        String errMessage = "[ERROR] 이동 명령은 정해진 문자만 가능합니다. INPUT : " + command;
        System.out.println(errMessage);
        throw new IllegalArgumentException();
    }

    private void validateStringGameCommand(String command) {
        for (GameCommand gameCommand : gameCommands) {
            if (command.equals(gameCommand.getCommand())) {
                return;
            }
        }
        String errMessage = "[ERROR] 재시작/종료 입력은 정해진 문자만 가능합니다.";
        System.out.println(errMessage);
        throw new IllegalArgumentException();
    }
}
