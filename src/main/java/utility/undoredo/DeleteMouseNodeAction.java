package utility.undoredo;

import mouse.MouseNode;

/**
 * User: Eptwalabha
 * Date: 29/07/2014
 * Time: 22:01
 */
public class DeleteMouseNodeAction extends UndoRedoAction {

    private MouseNode mouseNode;
    private MouseNode chain;
    private int index;

    public DeleteMouseNodeAction(MouseNode mouseNode) {
        this.mouseNode = mouseNode;
        chain = mouseNode;
        while (chain.getPrevious() != null) {
            chain = chain.getPrevious();
        }
        index = chain.indexOf(mouseNode);
    }

    @Override
    public void doAction() {

        if (index == 0) {
            chain = mouseNode.getNext();
            chain.setPrevious(null);
            mouseNode.clearLink();
            return;
        }

        MouseNode previous = mouseNode.getPrevious();
        MouseNode next = mouseNode.getNext();

        if (next != null) {
            previous.setNext(next);
        } else {
            previous.setNext(null);
        }

        mouseNode.clearLink();
    }

    @Override
    public void undoAction() {
        chain.insertNode(index, mouseNode);
    }
}
