from datetime import time

import keras
import pathlib
import tensorflow as tf

from keras import applications
from keras.applications import resnet
from numpy.random._examples.cffi.extending import state
from tensorflow.keras.applications.vgg16 import VGG16
from tensorflow.keras.models import Sequential, Model
from tensorflow.keras.layers import Dense, Dropout, Flatten, MaxPool2D, GlobalAveragePooling2D
from keras.layers.convolutional import Conv2D
from matplotlib import pyplot as plt
from tensorflow.python.framework.ops import disable_eager_execution

from tensorflow.keras.optimizers import SGD
from tensorflow.keras.preprocessing.image import ImageDataGenerator

import os
os.environ['TF_CPP_MIN_LOG_LEVEL'] = '2'

disable_eager_execution()

img_width = 100
img_height = 100
num_classes = 2
batch_size = 1

datagen = ImageDataGenerator(rescale=1/255.0, validation_split=0.2)  # using 20% of data as validation

train_data_generator = datagen.flow_from_directory(
    directory ='M:/Year 3/CNN/Data/nih-malaria', target_size =(img_width, img_height),#'/content/CE301/cell_images'
    class_mode = 'binary',
    batch_size = batch_size,
    subset = 'training'
) # Training subset

validation_data_generator = datagen.flow_from_directory(
    directory ='M:/Year 3/CNN/Data/nih-malaria', target_size =(img_width, img_height),#'/content/CE301/cell_images'
    class_mode = 'binary',
    batch_size = batch_size,
    subset = 'validation'
) # Validation subset#

sample = next(train_data_generator)
img = sample[0][0]
label = sample[1][0]
plt.imshow(img)
print(label)

base_model = VGG16(include_top=False, weights='imagenet', input_shape=(100,100,3))
base_model = Model(inputs=base_model.input, outputs=base_model.get_layer('block5_conv2').output)

base_model.summary()

tf.compat.v1.experimental.output_all_intermediates(False)

x = base_model.output
x = GlobalAveragePooling2D()(x)
# let's add a fully-connected layer
x = tf.keras.layers.Dense(1024, activation='relu')(x)
x = tf.keras.layers.Dropout(0.5)(x)
# and a logistic layer
predictions = tf.keras.layers.Dense(num_classes, activation='softmax', name='predictions')(x)
# this is the model we will train
model = Model(inputs=base_model.input, outputs=predictions)


for layer in base_model.layers:
    layer.trainable = False
# compile the model (should be done *after* setting layers to non-trainable)
#fix the optimizer
sgd = SGD(learning_rate=0.00001, decay=1e-6, momentum=0.9, nesterov=True)
#compile the gpu model
model.compile(optimizer=sgd,
              loss='mse',
              metrics=['accuracy'])

hist = model.fit_generator(generator=train_data_generator,
      epochs=6,
      validation_data = validation_data_generator,
      verbose=1)

print(hist.history)



