package engine.graph;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL20.glEnableVertexAttribArray;
import static org.lwjgl.opengl.GL30.*;

import org.lwjgl.system.MemoryUtil;

public class Mesh {

    private final int vaoId;

    private final int pos1VboId;

    private final int pos2VboId;

    private final int idxVboId;

    private final int vertexCount;

    private final int colourVboId;

    private final int mode;

    private int cullFace;

    public Mesh(float[] positions1, float[] positions2, float[] colors, int[] indices, int mode) {
        this.mode = mode;

        FloatBuffer pos1Buffer = null;
        FloatBuffer pos2Buffer = null;
        FloatBuffer colourBuffer = null;
        IntBuffer indicesBuffer = null;
        try {
            vertexCount = indices.length;

            vaoId = glGenVertexArrays();
            glBindVertexArray(vaoId);

            // Position VBO
            pos1VboId = glGenBuffers();
            pos1Buffer = MemoryUtil.memAllocFloat(positions1.length);
            pos1Buffer.put(positions1).flip();
            glBindBuffer(GL_ARRAY_BUFFER, pos1VboId);
            glBufferData(GL_ARRAY_BUFFER, pos1Buffer, GL_STATIC_DRAW);
            glEnableVertexAttribArray(0);
            glVertexAttribPointer(0, 3, GL_FLOAT, false, 0, 0);

            // Position VBO
            pos2VboId = glGenBuffers();
            pos2Buffer = MemoryUtil.memAllocFloat(positions2.length);
            pos2Buffer.put(positions2).flip();
            glBindBuffer(GL_ARRAY_BUFFER, pos2VboId);
            glBufferData(GL_ARRAY_BUFFER, pos2Buffer, GL_STATIC_DRAW);
            glEnableVertexAttribArray(0);
            glVertexAttribPointer(2, 3, GL_FLOAT, false, 0, 0);

            // Colour VBO
            colourVboId = glGenBuffers();
            colourBuffer = MemoryUtil.memAllocFloat(colors.length);
            colourBuffer.put(colors).flip();
            glBindBuffer(GL_ARRAY_BUFFER, colourVboId);
            glBufferData(GL_ARRAY_BUFFER, colourBuffer, GL_STATIC_DRAW);
            glEnableVertexAttribArray(1);
            glVertexAttribPointer(1, 4, GL_FLOAT, false, 0, 0);

            // Index VBO
            idxVboId = glGenBuffers();
            indicesBuffer = MemoryUtil.memAllocInt(indices.length);
            indicesBuffer.put(indices).flip();
            glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, idxVboId);
            glBufferData(GL_ELEMENT_ARRAY_BUFFER, indicesBuffer, GL_STATIC_DRAW);

            glBindBuffer(GL_ARRAY_BUFFER, 0);
            glBindVertexArray(0);
        } finally {
            if (pos1Buffer != null) {
                MemoryUtil.memFree(pos1Buffer);
            }
            if (pos2Buffer != null) {
                MemoryUtil.memFree(pos2Buffer);
            }
            if (colourBuffer != null) {
                MemoryUtil.memFree(colourBuffer);
            }
            if (indicesBuffer != null) {
                MemoryUtil.memFree(indicesBuffer);
            }
        }
    }

    public int getVaoId() {
        return vaoId;
    }

    public int getVertexCount() {
        return vertexCount;
    }

    public void cleanUp() {
        glDisableVertexAttribArray(0);

        // Delete the VBOs
        glBindBuffer(GL_ARRAY_BUFFER, 0);
        glDeleteBuffers(pos1VboId);
        glDeleteBuffers(pos2VboId);
        glDeleteBuffers(colourVboId);
        glDeleteBuffers(idxVboId);

        // Delete the VAO
        glBindVertexArray(0);
        glDeleteVertexArrays(vaoId);
    }

    public void render() {
        glCullFace(cullFace);
        // Draw the mesh
        glBindVertexArray(getVaoId());
        glEnableVertexAttribArray(0);
        glEnableVertexAttribArray(1);
        glEnableVertexAttribArray(2);

        glDrawElements(mode, getVertexCount(), GL_UNSIGNED_INT, 0);

        // Restore state
        glDisableVertexAttribArray(0);
        glDisableVertexAttribArray(1);
        glDisableVertexAttribArray(2);
        glBindVertexArray(0);
    }

    public void setCullFace(int cullFace) {
        this.cullFace = cullFace;
    }
}
