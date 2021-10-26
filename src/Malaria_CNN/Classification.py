import tensorflow as tf
import keras
from keras.models import Sequential
from keras.layers import Dense, Dropout, Flatten, MaxPool2D
from keras.layers.convolutional import Conv2D

from tensorflow.keras.optimizers import SGD
from tensorflow.keras.preprocessing.image import ImageDataGenerator

print(tf.__version__)

import numpy as np
import matplotlib.pyplot as plt


img_width = 100
img_height = 100

datagen = ImageDataGenerator(rescale=1/255.0, validation_split=0.2) # using 20% of data as validation

train_data_generator = datagen.flow_from_directory(
    directory='M:/Year 3/CNN/Data/nih-malaria', target_size =(img_width, img_height),
    class_mode= 'binary',
    batch_size= 16,
    subset= 'training'
) # Training subset

validation_data_generator = datagen.flow_from_directory(
    directory ='M:/Year 3/CNN/Data/nih-malaria', target_size =(img_width, img_height),
    class_mode = 'binary',
    batch_size = 16,
    subset = 'validation'
) # Validation subset

model = Sequential()

model.add(Conv2D(16,(3,3), input_shape =(img_width, img_height, 3), activation='relu'))
model.add(MaxPool2D(2,2))
model.add(Dropout(0.2))

model.add(Conv2D(32,(3,3), activation='relu'))
model.add(MaxPool2D(2,2))
model.add(Dropout(0.3))

model.add(Flatten())
model.add(Dense(64, activation='relu'))
model.add(Dropout(0.5))

model.add(Dense(1, activation='sigmoid'))

model.summary()

model.compile(optimizer='adam', loss='binary_crossentropy', metrics = ['accuracy'])

history = model.fit_generator(generator=train_data_generator,
                              steps_per_epoch = len(train_data_generator),
                              epochs = 5,
                              validation_data = validation_data_generator,
                              validation_steps = len(validation_data_generator))
