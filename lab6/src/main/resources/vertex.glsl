#version 330

layout (location =0) in vec3 position1;
layout (location =2) in vec3 position2;
layout (location =1) in vec4 inColour;

out vec4 exColour;

uniform mat4 modelMatrix;
uniform mat4 viewMatrix;
uniform mat4 projectionMatrix;
uniform float progress;

void main()
{
    vec3 point = mix(position1, position2, progress);
    gl_Position = projectionMatrix * viewMatrix * modelMatrix * vec4(point, 1.0);
    exColour = inColour;
}