import tensorflow as tf

tf.random.set_seed(1234)
import tensorflow.keras.datasets as tfds

import keras
from keras.models import Sequential
from keras.layers import Dense, Dropout, Flatten, MaxPool2D, GlobalAveragePooling2D, Activation, MaxPooling2D
from keras.layers.convolutional import Conv2D

from tensorflow.keras.optimizers import SGD, Adam
from tensorflow.keras.preprocessing.image import ImageDataGenerator, array_to_img, img_to_array, img_to_array, load_img
#from tensorflow.keras.layers import Resizing
#from tensorflow.keras.layers import Rescaling

print(tf.__version__)

import numpy as np
import matplotlib.pyplot as plt

img_width = 100
img_height = 100

#datagen = ImageDataGenerator(rescale=1/255.0, validation_split=0.2) # using 20% of data as validation

train_datagen = ImageDataGenerator(rescale=1/255.0,
                             rotation_range=10, # rotation
                             width_shift_range=0.2, # horizontal shift
                             height_shift_range=0.2, # vertical shift
                             zoom_range=0.2, # zoom
                             horizontal_flip=True, # horizontal flip
                             brightness_range=[0.2,1.2]) # brightness

validation_datagen = ImageDataGenerator(rescale=1/255.0,
                             validation_split=0.2) # using 20% of data as validation

train_data_generator = train_datagen.flow_from_directory(
    directory='M:/Year 3/CNN/Data/nih-malaria', target_size =(img_width, img_height),
    class_mode= 'binary',
    batch_size= 16,
    subset= 'training',
    seed = 1
) # Training subset

validation_data_generator = validation_datagen.flow_from_directory(
    directory ='M:/Year 3/CNN/Data/nih-malaria', target_size =(img_width, img_height),
    class_mode = 'binary',
    batch_size = 16,
    subset = 'validation',
    seed = 2
) # Validation subset

sample = next(train_data_generator)
img = sample[0][0]
label = sample[1][0]
plt.imshow(img)
print(label)


# model = Sequential()
#
# model.add(Conv2D(16,(3,3), input_shape =(img_width, img_height, 3), activation='relu'))
# model.add(MaxPool2D(2,2))
# model.add(Dropout(0.2))
#
# model.add(Conv2D(32,(3,3), activation='relu'))
# model.add(MaxPool2D(2,2))
# model.add(Dropout(0.3))
#
# model.add(Flatten())
# model.add(Dense(64, activation='relu'))
# model.add(Dropout(0.5))
#
# model.add(Dense(1, activation='sigmoid'))
#
# model.summary()
#
# model.compile(optimizer='adam', loss='binary_crossentropy', metrics = ['accuracy'])
#
# history = model.fit_generator(generator=train_data_generator,
#                               steps_per_epoch = len(train_data_generator),
#                               epochs = 5,
#                               validation_data = validation_data_generator,
#                               validation_steps = len(validation_data_generator))
#
# acc = history.history['accuracy']
# val_acc = history.history['val_accuracy']
#
# loss = history.history['loss']
# val_loss = history.history['val_loss']
#
# epochs_range = range(5)
#
# plt.figure(figsize=(8, 8))
# plt.subplot(1, 2, 1)
# plt.plot(epochs_range, acc, label='Training Accuracy')
# plt.plot(epochs_range, val_acc, label='Validation Accuracy')
# plt.legend(loc='lower right')
# plt.title('Training and Validation Accuracy')
#
# plt.subplot(1, 2, 2)
# plt.plot(epochs_range, loss, label='Training Loss')
# plt.plot(epochs_range, val_loss, label='Validation Loss')
# plt.legend(loc='upper right')
# plt.title('Training and Validation Loss')
# plt.show()

model = Sequential()

model.add(Conv2D(32, (3, 3), input_shape=(img_width, img_height,3)))
model.add(Activation('relu'))
model.add(MaxPooling2D(pool_size=(2, 2)))

model.add(Conv2D(32, (3, 3)))
model.add(Activation('relu'))
model.add(MaxPooling2D(pool_size=(2, 2)))

model.add(Conv2D(64, (3, 3)))
model.add(Activation('relu'))
model.add(MaxPooling2D(pool_size=(2, 2)))

model.add(Flatten())
model.add(Dense(64, activation='relu'))
model.add(Dropout(0.5))
model.add(Dense(2, activation='softmax'))

model.summary()

model.compile(optimizer='adam',
              loss='sparse_categorical_crossentropy',
              metrics=['accuracy'])

epochs = 15
history = model.fit(
    train_data_generator,
    validation_data=validation_data_generator,
    steps_per_epoch = len(train_data_generator),
    epochs=epochs
)

acc = history.history['accuracy']
val_acc = history.history['val_accuracy']

loss = history.history['loss']
val_loss = history.history['val_loss']

epochs_range = range(epochs)

plt.figure(figsize=(8, 8))
plt.subplot(1, 2, 1)
plt.plot(epochs_range, acc, label='Training Accuracy')
plt.plot(epochs_range, val_acc, label='Validation Accuracy')
plt.legend(loc='lower right')
plt.title('Training and Validation Accuracy')

plt.subplot(1, 2, 2)
plt.plot(epochs_range, loss, label='Training Loss')
plt.plot(epochs_range, val_loss, label='Validation Loss')
plt.legend(loc='upper right')
plt.title('Training and Validation Loss')
plt.show()



