#!/usr/bin/env python3

from optparse import OptionParser
import os
import re
import sys


def main():
    parser = OptionParser()
    parser.add_option('-r',
        action='store_true',
        dest='r',
        default=False,
        help='Looks in subdirectories recursively',
    )
    (options, args) = parser.parse_args()
    if len(args) != 2:
        parser.error('expected arguments: <filename_pattern> <replacement_pattern>')
    (filename_pattern, replacement_pattern) = args
    
    if options.r:
        def walkpaths():
            for (rootpath, dirnames, filenames) in os.walk('.', topdown=False):
                for filename in filenames:
                    yield os.path.join(rootpath, filename)
                for dirname in dirnames:
                    yield os.path.join(rootpath, dirname)
        filepaths = walkpaths()
    else:
        filepaths = os.listdir('.')
    
    # Determine rename operations
    rename_ops = []
    new_filepaths = []
    filename_pattern_re = re.compile(filename_pattern)
    for old_filepath in filepaths:
        old_filename = os.path.basename(old_filepath)
        new_filename = re.sub(filename_pattern_re, replacement_pattern, old_filename)
        if new_filename != old_filename:
            new_filepath = os.path.join(os.path.dirname(old_filepath), new_filename)
            
            rename_ops.append((old_filepath, new_filepath))
            new_filepaths.append(new_filepath)
    
    # Present proposed rename operations and confirm
    for (old_filepath, new_filepath) in rename_ops:
        print('%s => %s' % (old_filepath, new_filepath))
    print()
    if len(new_filepaths) != len(set(new_filepaths)):
        print('Multiple source files would be renamed to the same destination file. Aborted.')
        sys.exit(1)
    ok = input('Okay? [y/N] ')
    if ok.lower() != 'y':
        return
    
    # Perform rename operations
    for (old_filepath, new_filepath) in rename_ops:
        os.rename(old_filepath, new_filepath)


if __name__ == '__main__':
    main()
